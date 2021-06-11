package com.drosztmer.expensetracker.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
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

    // Variables for using data binding
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
        binding.viewmodel = mainViewModel

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createExpenseFragment)
        }
        setupRecyclerView()

        observeViewModel()

        setHasOptionsMenu(true)

        return binding.root
    }

    // Function for observing the viewmodel and setting the appropriate data for the adapter and views
    private fun observeViewModel() {
        mainViewModel.getAllExpenses.observe(viewLifecycleOwner, { data ->
            mainViewModel.checkIfDatabaseEmpty(data)
            data?.let {
                expenseAdapter.setData(data)
            }
        })
        mainViewModel.getSum.observe(viewLifecycleOwner, { sum ->
            val totalText = "${sum.toPlainString()} Ft"
            binding.totalText.text = totalText
        })

    }

    // Setting up recyclerview with it's adapter and layoutmanager
    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    // Inflating the menu with the delete all option
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    // Call appropriate function when selecting menuitem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    // Show alert dialog to confirm deleting all expenses
    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            mainViewModel.deleteAllExpenses()
            Toast.makeText(
                    requireContext(),
                    "Successfully deleted all expenses!",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.delete_everything))
        builder.setMessage(getString(R.string.delete_all_expenses))
        builder.create().show()
    }

    // Making binding null to avoid memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}