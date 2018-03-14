package mx.edu.ittepic.proyectounidad2.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.edu.ittepic.proyectounidad2.ActivityAgregarEmpleado;
import mx.edu.ittepic.proyectounidad2.AsignarEmpleado;
import mx.edu.ittepic.proyectounidad2.AsignarEmpleadoActividad;
import mx.edu.ittepic.proyectounidad2.Clases.Empleado;
import mx.edu.ittepic.proyectounidad2.R;

public class AdaptadorEmpleadoSeleccion extends RecyclerView.Adapter<AdaptadorEmpleadoSeleccion.ViewHolderClientes> implements View.OnClickListener {
    ArrayList<Empleado> listaClientes;
    private View.OnClickListener listener;
    Context context;
    public int positions;
    EditText text;

    public AdaptadorEmpleadoSeleccion(ArrayList<Empleado> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context=context;
    }


    public int getPosition() {
        return positions;
    }

    public void setPosition(int position) {
        this.positions = position;
    }


    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout= R.layout.vista_empleado2;
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        return new ViewHolderClientes(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderClientes holder, int position) {

        holder.nombre.setText(listaClientes.get(position).getNombree());
        holder.cel.setText(listaClientes.get(position).getCele());
        final int postemp=position;
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(postemp);
                return false;
            }
        });
        holder.seleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  setPosition(postemp);
               // int position=postemp;
                Intent abrir= new Intent(context,AsignarEmpleadoActividad.class);
                abrir.putExtra("idempleado",listaClientes.get(postemp).getIdempleado());
                abrir.putExtra("idobra",listaClientes.get(postemp).getIdobra());
                context.startActivity(abrir);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
    public class ViewHolderClientes extends RecyclerView.ViewHolder {
        CardView card;
        TextView nombre,cel;
        Button seleccion;
        public ViewHolderClientes(View itemView) {
            super(itemView);
            card= (CardView)  itemView.findViewById(R.id.card1);
            nombre= (TextView) itemView.findViewById(R.id.namee2);
            cel= (TextView) itemView.findViewById(R.id.cele2);
            seleccion= itemView.findViewById(R.id.btnagregar);

        }
    }
}