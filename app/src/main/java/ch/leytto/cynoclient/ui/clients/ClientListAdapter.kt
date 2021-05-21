package ch.leytto.cynoclient.ui.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Client

class ClientListAdapter(private val onClickListener: (Client) -> Unit) : ListAdapter<Client, ClientListAdapter.ClientViewHolder>(ClientComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = getItem(position)

        holder.itemView.setOnClickListener {
            client?.let { cli -> onClickListener.invoke(cli) }
        }

        holder.bind(client)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clientItemView: TextView = itemView.findViewById(R.id.client_name_textview)

        fun bind(client: Client) {
            val fullname = "${client.firstname} ${client.lastname}"
            clientItemView.text = fullname
        }

        companion object {
            fun create(parent: ViewGroup): ClientViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.client_recyclerview_item, parent, false)
                return ClientViewHolder(view)
            }
        }
    }

    class ClientComparator : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.firstname == newItem.firstname
        }
    }
}