using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct pro_t{
    string nom;
    int precio;
}
struct pedido_t{
    int numero;
    string nombre;
    int cant;
    int precio;
    string estado;
    date fecha;
};
void agregar(pro pedi[1][4], vector<pedido_t> &pedidos){
    int num=1;
    
    string product;
    string canti;
    pedido_t.numero=num;
    cout<<"Ingrese su nombre"<<endl;
    cin>>pedido_t.nombre;
          for(int z=0; z<2; z++){
            cout<<gondola[z].nom;
        
    }
    cout<<"ingrese el producto que quiera"<<endl;
    cin>>product;
    if(product="Pollo"){
        cout<<"ingrese cantidad"<<endl;
        cin>>pedido_t.cant;
        pre=cant*gondola[0].precio;
        pedido_t.precio=pre;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.dia;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.mes;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.anio;

    }
        if(product="salame"){
        cout<<"ingrese cantidad"<<endl;
        cin>>pedido_t.cant;
        pre=cant*gondola[1].precio;
        pedido_t.precio=pre;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.dia;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.mes;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.anio;

    }
        if(product="queso"){
        cout<<"ingrese cantidad"<<endl;
        cin>>pedido_t.cant;
        pre=cant*gondola[2].precio;
        pedido_t.precio=pre;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.dia;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.mes;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.anio;

    }
        if(product="Bife"){
        cout<<"ingrese cantidad"<<endl;
        cin>>pedido_t.cant;
        pre=cant*gondola[3].precio;
        pedido_t.precio=pre;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.dia;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.mes;
        cout<<"ingrese dia"<<endl;
        cin>>pedido_t.fecha.anio;

    }
    num=num+1;
    pedido_t.estado="encurso"
    
    
 
    pedidos.push_back(pedido_t);
}

void cancelar(vector <pedido_t> &pedidos){
    for (int i=0; i<pedidos.size(); i++){
        pedidos[i].estado="cancelado";
        
    }
}



void mostrar(vector <pedido_t> pedidos){
    int total=0;
    for(int i=0; i<pedidos.size();i++){
        if(pedidos[i].estado=="cancelado"){

        }
        else if(pedidos[i].estado=="completado"){
            if(pedidos[i].fecha.dia=17 and pedidos[i].fecha.mes=2 and pedidos[i].fecha.anio=2025){
            total=total+pedidos[i].precio;
            cout<<total<<endl;
            }
        }
        else{
            cout<<pedidos[i].numero<<'-'<<pedidos[i].nombre<<'-'<<pedidos[i].precio<<'-'<<pedidos[i].estado<<'-'<<pedidos[i].fecha.dia<<'/'<<pedidos[i].fecha.mes<<'/'<<pedidos[i].fecha.anio;
        }
        }
    }

   





int main(){
    int opcion=0;
    vector<pedido_t>pedidos;
    pro_t pedi[1][4]={
        { 
            {"Pollo", 1300},
            {"salame", 456},
            {"queso", 789},
            {"Bife", 1200}
        }
    };
    cout<<"Desea pedir nuevamente=1"<<endl;
    cout<<"Cancelar pedido=2"<<endl;
    cout<<"mostrar pedido=3"<<endl;
    cout<<"Salir=0"<<endl;
    cin>>opcion;
    if(opcion=1){
        cout<<pedidos[i].numero<<pedidos[i].nombre
    }
    if(opcion=2){
        cancelar(pedidos);
    }
    if(opcion=3){
        mostrar(pedidos);
    }
    
}
