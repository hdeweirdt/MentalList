package be.harm.mentallist.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import be.harm.mentallist.Injector
import be.harm.mentallist.databinding.FragmentListBinding

abstract class ListFragment : Fragment(), NewItemDialog.NewItemDialogListener {
    abstract val listId: Long
    private lateinit var binding: FragmentListBinding

    private lateinit var listViewModel: ListViewModel

    private lateinit var injector: Injector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector = Injector(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listRepository = injector.provideListRepository()
        val vmFactory = ListViewModelFactory(listId, listRepository)
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
        val adapter = ItemListAdapter()
        binding.rvItemList.adapter = adapter
        binding.rvItemList.layoutManager = LinearLayoutManager(requireContext())

        val swipeToDeleteHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter, listViewModel))
        swipeToDeleteHelper.attachToRecyclerView(binding.rvItemList)

        binding.rvItemList.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        listViewModel.items.observe(viewLifecycleOwner, Observer { itemList ->
            (binding.rvItemList.adapter as ItemListAdapter).submitList(itemList)
        })
    }

    override fun onStart() {
        super.onStart()
        binding.fabItemListAddItem.setOnClickListener {
            val addNewItemDialog = NewItemDialog()
            addNewItemDialog.setTargetFragment(this, 1)
            addNewItemDialog.show(fragmentManager!!, "AddNewItemDialog")
        }
    }

    override fun onAddItemClicked(newItemName: String) {
        listViewModel.addItemWithName(newItemName)
    }
}
