package com.example.databinding

interface ItemPresenter<T> {
    fun onClick(item: T)
}