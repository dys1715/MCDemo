package dys.mcdemo.view

import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import dys.mcdemo.R
import dys.mcdemo.databinding.ActivityMainBinding
import dys.mcdemo.frame.getToday
import dys.mcdemo.frame.showToast
import dys.mcdemo.view.fragment.FragmentHolder
import dys.mcdemo.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_navigation.*

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var fragmentHolder: FragmentHolder
    private var mExitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main)
        initFragments()
        //set text type
        tv_bar_title.text = "Today is ".plus(getToday())
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        //set navigation
        binding.rgRoot!!.rgRoot.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.rb_home -> {
                    rb_home.isChecked = true
                    supportFragmentManager.beginTransaction()
                            .show(homeFragment)
                            .hide(fragmentHolder)
                            .commit()
                }
                R.id.rb_find -> {
                    rb_find.isChecked = true
                    supportFragmentManager.beginTransaction()
                            .show(fragmentHolder)
                            .hide(homeFragment)
                            .commit()
                }
            }
        }
    }

    private fun initFragments() {
        homeFragment = HomeFragment.newInstance()
        fragmentHolder = FragmentHolder.newInstance("Find Page")
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, homeFragment)
                .add(R.id.fl_container, fragmentHolder)
                .show(homeFragment)
                .hide(fragmentHolder)
                .commitAllowingStateLoss()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
