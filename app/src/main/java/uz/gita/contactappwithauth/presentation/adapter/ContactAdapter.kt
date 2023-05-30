package uz.gita.contactappwithauth.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.databinding.ItemContactBinding
import javax.inject.Inject

class ContactAdapter @Inject constructor(): ListAdapter<ContactResponse,ContactAdapter.Holder>(MyDIffUtil) {
    private lateinit var onActionBtnClickedListener:(ContactResponse)->Unit
    object MyDIffUtil : DiffUtil.ItemCallback<ContactResponse>(){
        override fun areItemsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem.firstName == newItem.firstName
                    &&oldItem.lastName == newItem.lastName
                    &&oldItem.phone == newItem.phone
        }
    }

    inner class Holder(private val binding:ItemContactBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.imgAction.setOnClickListener {
                onActionBtnClickedListener.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind(position:Int){
            val item = getItem(position)
            binding.apply {
                txtFullName.text = "${item.firstName} ${item.lastName}"
                txtPhoneNumber.text = item.phone
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    fun setOnActionBtnClickedListener(block:(ContactResponse)->Unit){
        onActionBtnClickedListener = block
    }
}