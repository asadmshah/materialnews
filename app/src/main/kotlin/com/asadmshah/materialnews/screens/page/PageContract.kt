package com.asadmshah.materialnews.screens.page

interface PageContract {

    interface View {

        fun setHeroImage(url: String)

        fun setAdapter()

        fun notifyDataSetChanged()
    }

    interface Presenter {

        fun onCreate()

        fun onDestroy()

        fun getItemViewType(position: Int): Class<*>

        fun onBindViewHolder(viewHolder: HeroViewHolder, position: Int)

        fun onBindViewHolder(viewHolder: BasicViewHolder, position: Int)

        fun getItemCount(): Int

    }

}