using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct producto{
    int numero;
    string nombre;
    int precio;
    bool promo;
    int cantventa;
    string categoria;
};
struct pedido_t{
    int numero;
    string nombre;
    vector <int> cant;
    vector<string> produ;
    int precio;
    date fecha;
};

int totalpromo(vector <producto> productos, int canti){
    int total=0;
    for(int i=0; i<productos.size(); i++){
        if(productos[i].promo==1){
            total=(total+productos[i].precio*canti)*0.8;
        }
    }
    return total;
}
void agregarpedido(vector <producto> productos, vector <pedido_t> &pedidos){
    int num=1;
    int numprodu;
    pedido_t pedidido;
    string product;
    int canti=0;
    int preci=0;
    int preciototal=0;
    int precipromo=0;

    pedidido.numero=num;
    cout<<"desea pedir un producto,sino, ingrese salir"<<endl;
    cin>>product;

    while(product!="salir"){
        for(int i=0; i<productos.size(); i++){
            cout<<"---------------------------------------------------------------------------------"<<endl;
            cout<<i+1<<'-'<<productos[i].nombre<<'-'<<productos[i].precio<<'-'<<productos[i].promo<<endl;
        }
        
    cout<<"ingrese el nombre del producto que quiere, sino ingrese salir"<<endl;
    cin>>product;
    for(int i=0; i<productos.size(); i++){
        if(product==productos[i].nombre){
            cout<<"ingrese la cantidad que quiera"<<endl;
            cin>>canti;
            pedidido.cant.push_back(canti+' ');
            if(productos[i].promo==1){
                precipromo=totalpromo(productos, canti);
            }
        }
        else{
            preci=productos[i].precio*canti;
        }
    }
    pedidido.produ.push_back(product+ ' ');
       
    
    }
    
    preciototal=preci+precipromo;
    cout<<"Ingrese su nombre"<<endl;
    cin>>pedidido.nombre;
    pedidido.precio=preciototal;
    cout<<"ingrese dia del pedido"<<endl;
    cin>>pedidido.fecha.dia;
    cout<<"ingrese mes del pedido"<<endl;
    cin>>pedidido.fecha.mes;
    cout<<"ingrese anio del pedido"<<endl;
    cin>>pedidido.fecha.anio;
    num=num+1;

    

    
    pedidos.push_back(pedidido);
  
}

void ordenarpormayor(vector <producto> productos){
    int aux;
    string  auxnom;
    for(int i=0; i<productos.size();i++){
        for(int j=0; j<productos.size()-i;j++){
            if(productos[j].cantventa>productos[j+1].cantventa){
                aux=productos[j].cantventa;
                auxnom=productos[j].nombre;
                productos[j].cantventa=productos[j+1].cantventa;
                productos[j].nombre=productos[j+1].nombre;
                productos[j+1].cantventa=aux;
                productos[j+1].nombre=auxnom;
                
                
            }
     }
    }
    for(int z=0; z<productos.size();z++){
        cout<<productos[z].nombre<<'-'<<productos[z].cantventa<<'-'<<endl;
    }
    
} 
    



void mostraresp(vector <pedido_t> pedidos){
    string nom;
    cout<<"ingrese nombre del pedido a consultar"<<endl;
    cin>>nom;
    for(int i=0; i<pedidos.size();i++){
        if(pedidos[i].nombre==nom){
            cout<<pedidos[i].numero<<'-'<<pedidos[i].nombre<<'-'<<pedidos[i].fecha.dia<<'/'<<pedidos[i].fecha.mes<<'/'<<pedidos[i].fecha.anio<<endl;
            for(int j=0; j<pedidos[i].produ.size();j++){
                cout<<"---------------------------------------------------------------------------------"<<endl;
                cout<<pedidos[i].produ[j]<<'-'<<pedidos[i].cant[j]<<'-'<<pedidos[i].precio<<endl;
            }
    }
}
}



   





int main(){
    int opcion=1;

    vector<pedido_t>pedidos;
    vector<producto> productos = {
        {1,"BigMac",6,0,40,"Hamburguesas"},
        {2,"McNuggets",5,1,10,"Pollo"},
        {3,"McChicken",4,1,90,"Hamburguesas"},
        {4,"Quarter Pounder",5,0,30,"Hamburguesas"},
        {5,"Cajita Feliz",3,1,20,"Niños"},
        {6,"McPollo",3,0,40,"Pollo"},
        {7,"McWrap",4,1,30,"Pollo"},
        {8,"McRib",5,0,20,"Cerdo"},
        {9,"McSalad",3,1,10,"Ensaladas"},
        {10,"McMuffin",2,0,50,"Desayuno"},
        {11,"McToast",2,1,30,"Desayuno"},
        {12,"McBagel",3,0,20,"Desayuno"},
        {13,"McHotcakes",4,1,20,"Desayuno"},
        {14,"McSundae",2,0,30,"Postres"},
        {15,"McBrownie",2,1,20,"Postres"},
        {16,"McDonut",3,0,10,"Postres"},
        {17,"McMilkshake",4,1,20,"Bebidas"},
        {18,"McSmoothie",2,0,30,"Bebidas"},
        {19,"McTea",2,1,20,"Bebidas"},
        {20,"McWater",1,0,30,"Bebidas"},
        
        
    };
   
    
    while (opcion)
    {
        cout<<"Desea pedir=1"<<endl;
        cout<<"mostrar pedido=2"<<endl;
        cout<<"mostrar productos mas vendidos=3"<<endl;
        cout<<"Salir=0"<<endl;
        cin>>opcion;
        if(opcion==1){
            agregarpedido(productos,pedidos);
        }
    
        else if(opcion==2){
            mostraresp(pedidos);
        }
        else if(opcion==3){
            ordenarpormayor(productos);
        }

        else if(opcion==0){
            opcion=false;
        }
    }
}
