package com.example.viewpagertablayoutpro

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(val context: Context):RecyclerView.ItemDecoration() {
    //리사이클뷰에 만들어지고 나서 리사클러뷰 위에 화면을 배치
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //1.리사클러뷰 사이즈를 가져온다.
        val recyclerWidth = parent.width
        val recyclerHeight = parent.height

        //2.리사클러뷰 배치할 화면사이즈를 가져온다.
        val imageDate = ResourcesCompat.getDrawable(context.resources,R.drawable.img, null)
        val imageWidth = imageDate?.intrinsicWidth
        val imageHeight = imageDate?.intrinsicHeight
        //3. 화면을 배치할 리사클러뷰 화면 중심점을 구한다.
        val locateX = (recyclerWidth / 2) - (imageWidth?.div(2)as Int)
        val locatey = (recyclerHeight / 2) - (imageHeight?.div(2)as Int)

        //4. 캔버스에 중앙위치로 이미지를 그린다.
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.img), locateX.toFloat(),locatey.toFloat(), null)
    }
    //아이템뷰를 꾸미는 역할
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //1 꾸며야 할 아이템 뷰의 위치를 가져온다.
        val index = parent.getChildAdapterPosition(view)

        if(index % 2 == 0) {
            outRect.set(10,10,10,50)
        }else {
            outRect.set(10,10,10,0)
        }
        //뷰의 배경색을 설정한다.
        view.setBackgroundColor(Color.parseColor("#ffc107"))
        ViewCompat.setElevation(view,20.0f)
    }
}
