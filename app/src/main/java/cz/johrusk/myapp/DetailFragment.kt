package cz.johrusk.myapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Pepa on 02.07.2016.
 */
class DetailFragment : Fragment() {

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this); // Register EventBus instance to subscribe events
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.detail_fragment, container, false)
    }

    companion object {

        fun newInstance(): DetailFragment {
            val fragment = DetailFragment()

            return fragment
        }
    }

    // Gets body and title of selected items
    @Subscribe public fun onMessageEvent(event: MessageEvent) {
        if (event is MessageEvent) {
            tv_detail_title.setText(Html.fromHtml(event.title))
            tv_detail_body.setText(Html.fromHtml(event.body))
        }
    }
}