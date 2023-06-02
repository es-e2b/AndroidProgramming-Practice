package com.example.pragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class MyViewModel : ViewModel() {
    val myValue = MutableLiveData<String>()
    init {
        myValue.value = "hello"
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val viewModel : MyViewModel by viewModels()
    }
}

class Frag1Fragment : Fragment(R.layout.frag1_layout){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {

            val viewModel : MyViewModel by activityViewModels()
            viewModel.myValue.value="frag1.to.frag2"

            findNavController().navigate(R.id.action_frag1Fragment_to_frag2Fragment)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.myValue.observe(viewLifecycleOwner){
            println("frag1 ############# {$it} #############")
        }

    }
}

class Frag2Fragment : Fragment(R.layout.frag2_layout){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button2).setOnClickListener {

            val viewModel : MyViewModel by activityViewModels()
            viewModel.myValue.value="frag2.to.frag1"


            findNavController().navigate(R.id.action_frag2Fragment_to_frag1Fragment)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.myValue.observe(viewLifecycleOwner){
            println("frag2 ############# {$it} #############")
        }
    }
}