package com.amandaluz.wallpaperapplication.ui.fragments.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.amandaluz.core.module.PhotoDomain
import com.amandaluz.wallpaperapplication.databinding.FragmentGalleryBinding
import com.amandaluz.wallpaperapplication.framework.workmanager.WallpaperWork
import com.amandaluz.wallpaperapplication.ui.fragments.adapter.galleryadapter.GalleryAdapter
import com.amandaluz.wallpaperapplication.ui.fragments.gallery.viewmodel.GalleryViewModel
import com.amandaluz.wallpaperapplication.util.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val WORK_NAME = "WALLPAPER_WORK"
@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var binding : FragmentGalleryBinding
    private lateinit var galleryAdapter : GalleryAdapter

    @Inject
    lateinit var workManager : WorkManager

    private val viewModel : GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        initAdapter()
        getAllPhotos()
        backButton()
        startWorker(workManager)
    }

    private fun getAllPhotos(){
        viewModel.state.observe(viewLifecycleOwner){uiState->
            when (uiState){
                is GalleryViewModel.UiState.ShowGallery -> {
                    galleryAdapter.submitList(uiState.photo)
                }
                is GalleryViewModel.UiState.EmptyGallery -> {
                    galleryAdapter.submitList(emptyList())
                }
                GalleryViewModel.UiState.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initAdapter(){
        galleryAdapter = GalleryAdapter(::detail, ::delete)
        val gridLayout = GridLayoutManager(requireContext(), 3)
        with(binding.galleryRv){
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = galleryAdapter
        }
    }

    private fun detail(photoDomain : PhotoDomain){
        val data = arrayOf(photoDomain.srcDomain?.original, photoDomain.description)
        findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToDownloadFragment(data))
    }
    private fun delete(photoDomain : PhotoDomain){
        val dialog = CustomDialog(photoDomain){
            viewModel.delete(photoDomain)
        }
        dialog.show(childFragmentManager, "DELETE_PHOTO")
    }

    private fun backButton(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun startWorker(workManager : WorkManager){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val wallpaperWorker =
            PeriodicWorkRequest.Builder(WallpaperWork::class.java, 1, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(WORK_NAME,ExistingPeriodicWorkPolicy.UPDATE, wallpaperWorker)
    }

    private fun cancelWorker(workManager : WorkManager){
        workManager.cancelUniqueWork(WORK_NAME)
    }

}