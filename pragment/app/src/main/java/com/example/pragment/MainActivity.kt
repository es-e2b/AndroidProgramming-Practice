package com.example.pragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

class homeFragment : Fragment(R.layout.home_fragment){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : MyViewModel by activityViewModels()
        viewModel.myValue.observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        })
        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_homeFragment_to_nav1Fragment)
        }
    }
}
class nav1Fragment : Fragment(R.layout.nav1_fragment){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : MyViewModel by activityViewModels()
        viewModel.myValue.observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        })

        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_nav1Fragment_to_nav2Fragment)
        }
    }
}
class nav2Fragment : Fragment(R.layout.nav2_fragment){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : MyViewModel by activityViewModels()
        viewModel.myValue.observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        })

        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_nav2Fragment_to_homeFragment)
        }
    }
}