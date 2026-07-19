package com.example.itcoursetestapp.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.itcoursetestapp.presentation.databinding.ItemCourseBinding
import com.example.itcoursetestapp.presentation.databinding.ItemCourseSkeletonBinding
import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.CourseItem
import com.example.itcoursetestapp.presentation.home.adapter.HomeListItem.SkeletonItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class HomeDiffCallback : DiffUtil.ItemCallback<HomeListItem>() {
    override fun areItemsTheSame(oldItem: HomeListItem, newItem: HomeListItem): Boolean {
        if (oldItem is CourseItem && newItem is CourseItem) {
            return oldItem.course.id == newItem.course.id
        }
        return oldItem is SkeletonItem && newItem is SkeletonItem
    }

    override fun areContentsTheSame(oldItem: HomeListItem, newItem: HomeListItem): Boolean {
        return oldItem == newItem
    }
}

fun courseAdapterDelegate(
    onCourseClick: (CourseItem) -> Unit,
    onLikeClick: (CourseItem) -> Unit
) = adapterDelegateViewBinding<CourseItem, HomeListItem, ItemCourseBinding>(
    { layoutInflater, root -> ItemCourseBinding.inflate(layoutInflater, root, false) }
) {
    binding.root.setOnClickListener {
        onCourseClick(item)
    }
    binding.BookmarkBv.setOnClickListener {
        onLikeClick(item)
    }
    
    bind {
        binding.CourseTitleTv.text = item.course.title
        binding.CourseDescTv.text = item.course.text
        binding.CoursePriceTv.text = binding.root.context.getString(
            com.example.itcoursetestapp.presentation.R.string.course_price, 
            item.course.price
        )
        binding.RatingTv.text = item.course.rate

        binding.DateTv.text = item.course.publishDate

        val bookmarkRes = if (item.course.hasLike) {
            com.example.itcoursetestapp.presentation.R.drawable.ic_bookmark_filled
        } else {
            com.example.itcoursetestapp.presentation.R.drawable.ic_bookmark
        }

        binding.BookmarkIconIv.setImageResource(bookmarkRes)
    }
}

fun skeletonAdapterDelegate() = adapterDelegateViewBinding<SkeletonItem, HomeListItem, ItemCourseSkeletonBinding>(
    { layoutInflater, root -> ItemCourseSkeletonBinding.inflate(layoutInflater, root, false) }
) { }