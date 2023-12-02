package com.example.s.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.s.player.databinding.FragmentSleepTimerBinding
import com.example.s.player.databinding.SleepTimerDialogBinding

class SleepTimerFragment : Fragment() {

    lateinit var binding: FragmentSleepTimerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepTimerBinding.inflate(inflater, container, false)


        if (binding.radioCloseTimer.isChecked) {
            binding.radio10.isChecked = false
            binding.radio15.isChecked = false
            binding.radio30.isChecked = false
            binding.radio60.isChecked = false
        }
        if (binding.radio15.isChecked) {
            binding.radioCloseTimer.isChecked = false
            binding.radio10.isChecked = false
            binding.radio30.isChecked = false
            binding.radio60.isChecked = false
        }
        if (binding.radio15.isChecked) {
            binding.radioCloseTimer.isChecked = false
            binding.radio10.isChecked = false
            binding.radio30.isChecked = false
            binding.radio60.isChecked = false
        }
        if (binding.radio30.isChecked) {
            binding.radioCloseTimer.isChecked = false
            binding.radio15.isChecked = false
            binding.radio10.isChecked = false
            binding.radio60.isChecked = false
        }
        if (binding.radio60.isChecked) {
            binding.radioCloseTimer.isChecked = false
            binding.radio15.isChecked = false
            binding.radio30.isChecked = false
            binding.radio10.isChecked = false
        }


        val shredPreference = requireContext().getSharedPreferences("sdsd", 0).edit()
        val gShredPreference = requireContext().getSharedPreferences("sdsd", 0)


        binding.radio10.setOnClickListener {
            shredPreference.putBoolean("check", true)
            Toast.makeText(requireContext(), "Clicked fire", Toast.LENGTH_SHORT).show()
            shredPreference.apply()
        }
        gShredPreference.getBoolean("check", true)
        if (gShredPreference.contains("check")) {
            binding.radio10.isChecked = true
            Toast.makeText(requireContext(), "Contains", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(requireContext(), "Not Contains", Toast.LENGTH_SHORT).show()


        return binding.root
    }

}