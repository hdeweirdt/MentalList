package be.harm.mentallist.list.todoList

import androidx.lifecycle.ViewModelProviders
import be.harm.mentallist.TODO_LIST_ID
import be.harm.mentallist.list.ListFragment

private const val DEFAULT_NUMBER_OF_ITEMS_TO_SHOW = 3

class TodoListFragment : ListFragment() {

    override val listId: Long = TODO_LIST_ID

    override fun setUpViewModel() {
        val listRepository = injector.provideListRepository()
        val vmFactory =
            LimitedListViewModelFactory(listId, listRepository, DEFAULT_NUMBER_OF_ITEMS_TO_SHOW)
        listViewModel = ViewModelProviders.of(this, vmFactory).get(LimitedListViewModel::class.java)
    }
}
