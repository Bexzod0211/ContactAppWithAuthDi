package uz.gita.contactappwithauth.presentation.screens.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.databinding.ScreenMainBinding
import uz.gita.contactappwithauth.presentation.adapter.ContactAdapter
import uz.gita.contactappwithauth.presentation.dialogs.ActionsDialog
import uz.gita.contactappwithauth.presentation.dialogs.AddContactDialog
import uz.gita.contactappwithauth.presentation.dialogs.EditContactDialog
import uz.gita.contactappwithauth.presentation.viewmodels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(ScreenMainBinding::bind)
    @Inject
    lateinit var adapter:ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.logOutLiveData.observe(this,logOutObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.apply {
            recyclerMain.adapter = adapter
            recyclerMain.layoutManager = LinearLayoutManager(requireContext())
        }

        attachClickListeners()

        adapter.setOnActionBtnClickedListener {
            viewModel.btnActionsClicked(it)
        }

        viewModel.allContactsLiveData.observe(viewLifecycleOwner,allContactsObserver)
        viewModel.progressBarLiveData.observe(viewLifecycleOwner,progressBarObserver)
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        viewModel.openAddDialogLiveData.observe(viewLifecycleOwner,openAddDialogObserver)
        viewModel.actionBtnLiveData.observe(viewLifecycleOwner,actionBtnObserver)
        viewModel.editDialogLiveData.observe(viewLifecycleOwner,editDialogObserver)
        viewModel.deleteDialogLiveData.observe(viewLifecycleOwner,deleteDialogObserver)
        viewModel.logOutDialogLiveData.observe(viewLifecycleOwner,logOutDialogObserver)
    }


    private fun attachClickListeners() {
        binding.apply {
            bntAdd.setOnClickListener {
                viewModel.btnAddClicked()
            }
            btnLogOut.setOnClickListener {
                viewModel.btnLogOutClicked()
            }

        }

    }


    private val allContactsObserver = Observer<List<ContactResponse>> {
        adapter.submitList(it)
    }

    private val progressBarObserver = Observer<Boolean> {
        if (it == true){
            binding.progressBar.show()
        }else {
            binding.progressBar.hide()
        }
    }

    private val toastObserver = Observer<String> {
        Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
    }

    private val openAddDialogObserver = Observer<Unit> {
        val dialog = AddContactDialog()
        dialog.setOnBtnSubmitClickListener {
            viewModel.addContact(it)
        }
        dialog.show(childFragmentManager,"add")
    }

    private val actionBtnObserver = Observer<ContactResponse> {
        val dialog = ActionsDialog(it)

        dialog.setOnEditButtonClickListener {
            viewModel.btnEditClicked(it)
        }
        dialog.setOnDeleteButtonClickListener {
            viewModel.btnDeleteClicked(it)
        }

        dialog.show(childFragmentManager,"action")
    }

    private val editDialogObserver = Observer<ContactResponse> {
        val dialog = EditContactDialog(it,requireContext())
        dialog.setOnBtnSubmitClickListener {request->
            viewModel.updateContact(request)
            dialog.cancel()
        }
        dialog.show()
    }

    private val deleteDialogObserver = Observer<ContactResponse> {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Delete 1 item?")
            .setPositiveButton("Yes"){d,_->
                viewModel.deleteContact(it.id)
                d.cancel()
            }
            .setNegativeButton("No"){d,_->
                d.cancel()
            }
            .show()
    }

    private val logOutDialogObserver = Observer<Unit> {
        AlertDialog.Builder(requireContext())
            .setTitle("Log out")
            .setMessage("Are you sure want to log out?")
            .setPositiveButton("Yes"){d,_->
                d.cancel()
                viewModel.logOut()
            }
            .setNegativeButton("No"){d,_->
                d.cancel()
            }
            .show()
    }

    private val logOutObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
    }
}