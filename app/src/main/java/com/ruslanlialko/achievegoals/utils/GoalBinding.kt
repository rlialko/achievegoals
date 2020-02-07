package com.ruslanlialko.achievegoals.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.presentation.list.adapter.GoalsAdapter


@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<Goal>) {
    (recyclerView.adapter as GoalsAdapter).submitList(items)
}
//
//@BindingAdapter("app:imageUrl")
//fun loadImage(imageView: ImageView, url: String) {
//    GlideApp.with(imageView).load(url).placeholder(R.drawable.image_placeholder)
//        .error(R.drawable.image_placeholder).centerInside().into(imageView)
//}