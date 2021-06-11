package com.drosztmer.expensetracker.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drosztmer.expensetracker.R
import com.drosztmer.expensetracker.adapters.ExpenseAdapter
import com.drosztmer.expensetracker.databinding.FragmentHomeBinding
import com.drosztmer.expensetracker.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val expenseAdapter: ExpenseAdapter by lazy { ExpenseAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createExpenseFragment)
        }
        setupRecyclerView()

        observeViewModel()

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun observeViewModel() {
        mainViewModel.getAllExpenses.observe(viewLifecycleOwner, { data ->
            expenseAdapter.setData(data)
        })
        mainViewModel.getSum.observe(viewLifecycleOwner, { data ->
            val totalText = "$data Ft"
            binding.totalText.text = totalText
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}