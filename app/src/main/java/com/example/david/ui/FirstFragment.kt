package com.example.david.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.david.R
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products
import com.example.david.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), ProductsAdapter.PassTheData {

    lateinit var mViewModel: ProductsViewModel
    lateinit var mAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        mAdapter= ProductsAdapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView1 = recyclerView1
        recyclerView1.adapter= mAdapter
        recyclerView1.layoutManager= GridLayoutManager(context,1)
//        recyclerView1.setHasFixedSize(true)

        mViewModel.livedataFromLocal.observe(viewLifecycleOwner, Observer {
            Log.d("Fromdb",it.toString())
            mAdapter.updateAdapter(it)
        })

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun passProducts(products: Products) {
        val bundle= Bundle()
        bundle.putInt("id",products.id)

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }
}