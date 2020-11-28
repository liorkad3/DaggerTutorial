package com.example.daggertutorial.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggertutorial.R
import com.example.daggertutorial.databinding.FragmentUsersBinding
import com.example.daggertutorial.model.User
import com.example.daggertutorial.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class UsersFragment: Fragment() {

    private val viewModel: UsersViewModel by viewModels()
    private var _binding: FragmentUsersBinding? = null
    private lateinit var adapter: UsersAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpUi()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.users.observe(requireActivity(), Observer {
            when (it.status){
                Status.SUCCESS ->{
                    it.data?.let { d -> renderList(d) }
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun setUpUi() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
            requireContext(), (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun renderList(users: List<User>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}