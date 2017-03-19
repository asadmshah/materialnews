package com.asadmshah.materialnews.widgets

import android.content.Context
import android.graphics.*
import android.support.v8.renderscript.Allocation
import android.support.v8.renderscript.Element
import android.support.v8.renderscript.RenderScript
import android.support.v8.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import android.view.View

class BlurView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val paint = {
        val p = Paint()
        p.flags = Paint.FILTER_BITMAP_FLAG or Paint.ANTI_ALIAS_FLAG
        p.colorFilter = PorterDuffColorFilter(Color.argb(66, 255, 255, 255), PorterDuff.Mode.SRC_ATOP)
        p
    }()

    private lateinit var renderScript: RenderScript
    private lateinit var scriptIntrinsicBlur: ScriptIntrinsicBlur
    private var bitmap: Bitmap? = null
    private var sampling = 1

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        renderScript = RenderScript.create(context)
        scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
    }

    override fun onDraw(canvas: Canvas) {
        bitmap?.let {
            val s = sampling.toFloat()
            canvas.save()
            canvas.scale(s * 1.05f, s * 1.05f)
            canvas.drawBitmap(it, 0f, 0f, paint)
            canvas.restore()

        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scriptIntrinsicBlur.destroy()
        renderScript.destroy()
    }

    fun clearSource() {
        bitmap = null
        invalidate()
    }

    fun setBlurSource(view: View, sampling: Int, radius: Float) {
        if (view.width > 0 && view.height > 0) {
            this.sampling = sampling

            val w = view.width / sampling
            val h = view.height / sampling

            val src = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val dst = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

            val cvs = Canvas(src)
            cvs.scale(1f / sampling, 1f / sampling)
            view.draw(cvs)

            val allocSrc = Allocation.createFromBitmap(renderScript, src)
            val allocDst = Allocation.createFromBitmap(renderScript, dst)

            scriptIntrinsicBlur.setRadius(radius)
            scriptIntrinsicBlur.setInput(allocSrc)
            scriptIntrinsicBlur.forEach(allocDst)

            bitmap = dst
        }
        invalidate()
    }

}