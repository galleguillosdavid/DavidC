package com.example.david.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.david.R
import com.example.david.repository.localWithRoom.networkWithRetrofit.Products
import kotlinx.android.synthetic.main.product_item_list.view.*

class ProductsAdapter(val mPassTheData: PassTheData): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var productList= emptyList<Products>()

    fun updateAdapter(mList: List<Products>){
        productList= mList
        notifyDataSetChanged()
    }

    inner class ProductsViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image1 = itemView.imageView1
        val text1 = itemView.textView1
        val clickListener = itemView.setOnClickListener {
            mPassTheData.passProducts(productList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.product_item_list,parent,false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val productsOne= productList[position]
        Glide.with(holder.itemView.context).load(productsOne.image).into(holder.image1)
        holder.text1.text= productsOne.name
    }

    override fun getItemCount()= productList.size

    interface PassTheData {
        fun passProducts(products: Products)
    }
}