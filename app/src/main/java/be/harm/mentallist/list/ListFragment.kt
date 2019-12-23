package be.harm.mentallist.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import be.harm.mentallist.Injector
import be.harm.mentallist.databinding.FragmentListBinding

private const val SHOPPING_LIST_ID = 0L
private const val TODO_LIST_ID = 1L

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private lateinit var listViewModel: ListViewModel

    private lateinit var injector: Injector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector = Injector(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listRepository = injector.provideListDatabase()
        val vmFactory = ListViewModelFactory(SHOPPING_LIST_ID, listRepository)
        listViewModel = ViewModelProviders.of(this, vmFactory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.rvItemList.adapter = ItemListAdapter()
        binding.rvItemList.layoutManager = LinearLayoutManager(requireContext())

        listViewModel.items.observe(this, Observer { itemList ->
            (binding.rvItemList.adapter as ItemListAdapter).submitList(itemList)
        })
    }

    override fun onStart() {
        super.onStart()
        binding.fabItemListAddItem.setOnClickListener {
            listViewModel.addItemWithName("testNaam")
        }
    }
}
