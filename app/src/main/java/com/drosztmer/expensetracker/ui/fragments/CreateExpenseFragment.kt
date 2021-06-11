package com.drosztmer.expensetracker.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.drosztmer.expensetracker.R
import com.drosztmer.expensetracker.data.model.Expense
import com.drosztmer.expensetracker.databinding.FragmentCreateExpenseBinding
import com.drosztmer.expensetracker.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class CreateExpenseFragment : Fragment() {

    // Variables for using data binding
    private var _binding: FragmentCreateExpenseBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateExpenseBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            insertDataToDb()
        }

        return binding.root
    }

    // Inserting the new data to database
    private fun insertDataToDb() {
        // Getting data for creation of new entry to database
        var date = Date()
        val formatter = SimpleDateFormat("yyyy.MM.dd.")
        var timeCreated = formatter.format(date)
        val title = binding.titleEdittext.text.toString()
        val amountText = binding.priceEdittext.text.toString()
        val validation = verifyDataFromUser(title, amountText)

        // Checking if user didn't leave anything blank, then inserting new entry to database
        if (validation) {
            val newExpense = Expense(0, timeCreated, title, amountText.toInt())
            mainViewModel.insertExpense(newExpense)
            Toast.makeText(requireContext(), getString(R.string.expense_Added_succesfully), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createExpenseFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to make sure that the user doesn't leave anything blank
    private fun verifyDataFromUser(title: String, amount: String): Boolean {
        return !(title.isEmpty() || amount.isEmpty())
    }

    // Making binding null to avoid memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}