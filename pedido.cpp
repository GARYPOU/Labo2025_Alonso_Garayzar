using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct pro{
    string nom;
    int precio;
};
struct pedido_t{
    int numero;
    string nombre;
    int cant;
    int precio;
    string estado;
    date fecha;
};
void agregar(int num, pro pedi[1][4], vector<pedido_t> &pedidos){

    pedido_t pedidido;
    string product;
    int canti=0;
    int cantid=0;
    int preci=0;
    int preciototal=0;

    pedidido.numero=num;
    cout<<"Ingrese su nombre"<<endl;
    cin>>pedidido.nombre;
          for(int z=0; z<4; z++){
            cout<<pedi[0][z].nom+' ';
        
    }
    cout<<' '<<endl;
    cout<<"desea pedir un producto,sino, ingrese salir"<<endl;
    cin>>product;
    while(product!="salir"){
    cout<<"ingrese el producto que quiera, cuando no quiera mas ingrese salir"<<endl;
    cin>>product;
        for(int i=0; i<4; i++){
            if(product==pedi[0][i].nom){
            cout<<"ingrese la cantidad que quiera"<<endl;
            cin>>canti;
            preci=pedi[0][i].precio*canti;
            preciototal=preciototal+preci;
            pedidido.precio=preciototal;
            cantid=cantid+canti;
            pedidido.cant=cantid;            
            }
        }
    }
    cout<<"ingrese dia del pedido"<<endl;
    cin>>pedidido.fecha.dia;
    cout<<"ingrese mes del pedido"<<endl;
    cin>>pedidido.fecha.mes;
    cout<<"ingrese anio del pedido"<<endl;
    cin>>pedidido.fecha.anio;
    num=num+1;
    pedidido.estado="encurso";
    

    
    pedidos.push_back(pedidido);
  
}

void cancelar(vector <pedido_t> &pedidos){
    int num=0;
  cout<<"ingrese numero de pedido"<<endl;
    cin>>num;
    for(int i=0; i<pedidos.size();i++){
       if(pedidos[i].numero==num){
        pedidos[i].estado="cancelado";
    }
}
}


void mostrar(vector <pedido_t> pedidos){
    int total=0;
    for(int i=0; i<pedidos.size();i++){
        if(pedidos[i].estado=="completado"){
            if(pedidos[i].fecha.dia==17 and pedidos[i].fecha.mes==03 and pedidos[i].fecha.anio==2025){
                total=total+pedidos[i].precio;
                cout<<total<<endl;
            }
        }
        else if(pedidos[i].estado=="encurso"){
         cout<<pedidos[i].numero<<'-'<<pedidos[i].nombre<<'-'<<pedidos[i].precio<<'-'<<pedidos[i].estado<<'-'<<pedidos[i].fecha.dia<<'/'<<pedidos[i].fecha.mes<<'/'<<pedidos[i].fecha.anio<<endl;
        }
    }
}

void comp(vector <pedido_t> &pedidos){
    int num;
    cout<<"ingrese numero de pedido"<<endl;
    cin>>num;
    for(int i=0; i<pedidos.size();i++){
       if(pedidos[i].numero==num){
        pedidos[i].estado="completado";
       }
    }
}

   





int main(){
    int opcion=1;
    int num=1;
    vector<pedido_t>pedidos;
    pro pedi[1][4]={
        { 
            {"Pollo", 1300},
            {"salame", 456},
            {"queso", 789},
            {"Bife", 1200}
        }
    };
    
    while (opcion)
    {
        cout<<"Desea pedir=1"<<endl;
        cout<<"Cancelar pedido=2"<<endl;
        cout<<"mostrar pedido=3"<<endl;
        cout<<"se completo su pedido=4"<<endl;
        cout<<"Salir=0"<<endl;
        cin>>opcion;
        if(opcion==1){
            agregar(num, pedi, pedidos);
        }
        else if(opcion==2){
            cancelar(pedidos);
        }
        else if(opcion==3){
            mostrar(pedidos);
        }
        else if(opcion==4){
            comp(pedidos);
        }
        else if(opcion==0){
            opcion=false;
        }
    }
}
