package com.example.fridgetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.databinding.FragmentContentListBinding

class ContentList : Fragment() {

    private lateinit var binding: FragmentContentListBinding
    private lateinit var contentsViewModel: ContentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        contentsViewModel = ViewModelProvider(activity)[ContentsViewModel::class.java]
        setRecyclerView()
    }

    private fun setRecyclerView(){
        val mainActivity = this
        contentsViewModel.contentItems.observe(viewLifecycleOwner){
            binding.contentListRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ContentItemAdapter(it)
            }
        }
    }
}