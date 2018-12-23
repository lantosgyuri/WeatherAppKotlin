package com.lantosgyuri.weatherappkotlin.ui.weather.future.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lantosgyuri.weatherappkotlin.R

class FutureLsitWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = FutureLsitWeatherFragment()
    }

    private lateinit var viewModel: FutureLsitWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.future_lsit_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutureLsitWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
