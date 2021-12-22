package com.example.recyclerview.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.adapter.TextListAdapter
import com.example.recyclerview.databinding.FragmentListBinding
import com.example.recyclerview.model.TextItem
import kotlin.random.Random

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding) {
        "View was destroyed"
    }

//    private val adapter = TextAdapter()
    private val adapter = TextListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.menu_refresh) {
//                    adapter.setItems(
//                        items = List(20) {
//                            TextItem(text = Random.nextInt(0, 19).toString())
//                        }
//                    )

                    adapter.submitList(
                        List(20) {
                            TextItem(text = Random.nextInt(0, 19).toString())
                        }
                    )
                    true
                } else false
            }

            recyclerView.adapter = adapter
            recyclerView.addHorizontalSpaceDecoration(RECYCLER_ITEM_SPACE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val RECYCLER_ITEM_SPACE = 50
    }
}

fun RecyclerView.addHorizontalSpaceDecoration(space: Int) {
    addItemDecoration(
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                if (position != 0 && position != parent.adapter?.itemCount) {
                    outRect.top = space
                }
            }
        }
    )
}