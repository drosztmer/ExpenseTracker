package com.drosztmer.expensetracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.drosztmer.expensetracker.R
import com.drosztmer.expensetracker.data.model.Expense
import com.drosztmer.expensetracker.databinding.FragmentCreateExpenseBinding
import com.drosztmer.expensetracker.databinding.FragmentUpdateExpenseBinding
import com.drosztmer.expensetracker.util.Util
import com.drosztmer.expensetracker.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateExpenseFragment : Fragment() {

    // Variables for using data binding
    private var _binding: FragmentUpdateExpenseBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateExpenseFragmentArgs>()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateExpenseBinding.inflate(inflater, container, false)
        binding.args = args
        binding.price = args.expense.price.toString()
        binding.buttonUpdate.setOnClickListener {
            updateExpense()
        }
        return binding.root
    }

    private fun updateExpense() {
        // Getting data for creation of new entry to database
        val timeCreated = Util.createCurrentDateText()
        val title = binding.titleEdittext.text.toString()
        val priceText = binding.priceEdittext.text.toString()
        val validation = Util.verifyDataFromUser(title, priceText)

        // Check if user didn't leave anything blank, then inserting new entry to database
        if (validation) {
            val updatedItem = Expense(
                    args.expense.id, timeCreated, title, priceText.toBigDecimal())
            mainViewModel.updateExpense(updatedItem)
            Toast.makeText(requireContext(), getString(R.string.expanse_updated_successfully), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateExpenseFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}