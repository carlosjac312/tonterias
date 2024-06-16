#include <iostream>
#include<vector>
#include<stdlib.h>
#include<time.h>
#include<fstream>
#include <string>

using namespace std;

void display(vector<vector<int>> b){//ESTA FUNCION COMO DICE EL NOMBRE ES PARA MOSTRAR EL SUDOKU EN LA PANTALLA
    cout << "LAS FILAS Y COLUMANS VAN DEL 1 AL 9 NO DEL 0 AL 8\n";
    cout << "|_______|________|________|" << "\n" << "|       |        |        |" << "\n";
    for(int i{0}; i<b.size(); i++){
        for(int f{0}; f<b.at(i).size(); f++){
            cout << "|" << b.at(i).at(f) << "|";
        }
        if(i==2||i==5||i==8){
            cout << "\n" << "|_______|________|________|" << "\n" << "|       |        |        |" << "\n";
        }
        else{
            cout << "\n" << "|-------|--------|--------|" << "\n";
        }
    }
}

void sudoku(vector<vector<int>> problem, vector<vector<int>> answer){
    int fila{0}, columna{0}, cuadrado{0}, total{0}, f1{0}, c2{0}, num{0}, enc{0}, minf{0}, maxf{0}, minc{0}, maxc{0};
    while (total!=81) {
        display(problem);
        cout << "Introduzca el numero:\n";
        cin >> num;
        cout << "Indique fila para colocar el numero:\n";
        cin >> f1;
        f1-=1;
        cout << "Indique columna para colocar el numero:\n";
        cin >> c2;
        c2-=1;
        problem.at(f1).at(c2)=num;
//DE LA LINEA 27 A LA 37 ES TODO PARA COLOCAR EL NUMERO QUE SE HAYA INTRODUCIDO EN LA POSICIÓN SELECCIONADA
        if(f1<3){
            minf=0;
            maxf=2;
        }
        if(f1>2 && f1<6){
            minf=3;
            maxf=5;
        }if(f1>5){
            minf=6;
            maxf=8;
        }
        if(c2<3){
            minc=0;
            maxc=2;
        }
        if(c2>2 && c2<6){
            minc=3;
            maxc=5;
        }if(c2>5){
            minc=6;
            maxc=8;
        }
//DE LA LINEA 39 A LA 60 ES PARA ES PARA SABER EN CUAL DE LOS CUADRANTES SE HA COLOCADO EL NUMERO
        for(int i{0}; i<9; i++){
            if(problem.at(f1).at(i)!=0) fila++;
            if(fila==9){
                for(int z{0}; z<9; z++){
                    if(problem.at(f1).at(z)!=answer.at(f1).at(z)){
                        cout << "FILA INCORRECTA HAS PERDIDO\n";
                        enc=1;
                        break;
                    }
                }
            }
            if(enc==1){
                break;
            }
            if(problem.at(i).at(c2)) columna++;
            if(columna==9){
                for(int w{0}; w<9; w++){
                    if(problem.at(w).at(c2)!=answer.at(w).at(c2)){
                        cout << "COLUMNA INCORRECTA HAS PERDIDO\n";
                        enc=1;
                        break;
                    }
                }
            }
            if(enc==1){
                break;
            }
        }
        fila=0;
        columna=0;
//DE LA FILA 62 A LA 91 ES PARA REVISAR SI LA FILA Y LA COLUMAN ESTÁN LLENAS, Y SI LO ESTAN REVISAR SI ESTÁN CORRECTAS
        for(int i{minf}; i<maxf+1; i++){
            for(int f{minc}; f<maxc+1; f++){
                if(problem.at(i).at(f)!=0) cuadrado++;
            }
        }
        for(int i{minf}; i<maxf+1; i++){
            if(enc==1){
                break;
            }
            if(cuadrado==9){
                for(int f{minc}; f<maxc+1; f++){
                    if(problem.at(i).at(f)!=answer.at(i).at(f)){
                        cout << "CUADRADO INCORRECTO HAS PERDIDO\n";
                        enc=1;
                        break;
                    }
                }
            }
        }
        cuadrado=0;
//DE LA 93 A LA 112 ES PARA VER SI EL CUADRANTE EN EL QUE SE COLOCO EL NUMERO ESTÁ LLENO, Y SI LO ESTÁ REVISAR SI ESTÁ CORRECTO
        if(enc==1){
            total=81;
        }
        else{
            for(int i{0}; i<problem.size(); i++){
                for(int f{0}; f<problem.at(i).size(); f++){
                    if(problem.at(i).at(f)!=0) total++;
                }
            }
            if(total!=81) total=0;
        }
//DE LA 114 HASTA LA 124 ES PARA REVISAR SI EL SUDOKU YA ESTÁ TERMIANDO O NO(LA OPCION DE enc==1 ES SI LO HA TERMINADO PORQUE HA HECHO ALGUNA FILA, COLUMAN O CAUDRANTE MAL)
    }
    if(enc==0) cout << "FELICIDADES HAS GANADO\n";
}

vector<vector<int>> leer(string a){//ESTA FUNCION ES LA QUE DEBERÍA LEER LA NOTA DE TEXTO. RESUMIENDO LO QUE HACE CUANDO HAY UN "-" PUSHEA EN EL VECTOR QUE LE TOCA UN 0 Y CUANDO HAY UN NUMERO LO PUSHE(CON LOS ESPACIOS NO HACE NADA)
    ifstream archivo;              //AÑADIR QUE EL TAMAÑO DE LA NOTA LO HICE CONTANDO TAMBIEN LOS ESPACIOS QUE IBAN A HABER EN LA NOTA POR ESO EL TAMAÑO DE UN BUCLE ES 18 Y NO 9
    string texto;                  //USE ESTE VIDEO https://www.youtube.com/watch?v=GaqgqQL3wnQ&t=757s Y ESTE https://www.youtube.com/watch?v=ksnBUo-08Uw PARA CREAR LA FUNCIÓN. NO FUNCIONA AUN SAÍ TE DEJO EL CODIGO.
    vector<vector<int>> sudok{{},{},{},{},{},{},{},{},{}};
    int pos{0};

    if(archivo.fail()){
        cout << "No se pudo abrir el archivo\n";
        exit(1);
    }

    while (!archivo.eof()) {
        getline(archivo,texto);
        for(int i{0}; i<9; i++){
            for(int f{0}; f<18; f++){
                if(texto.at(pos)=='-'){
                    sudok.at(i).push_back(0);
                }
                if(texto.at(pos)!=' '||texto.at(pos)!='-'){
                    sudok.at(i).push_back(stoi(string (1,texto.at(pos))));
                }
                pos++;
            }
        }
    }
    return sudok;
}
int main()
{
    vector<vector<int>> sud1{{0,6,0,1,0,4,0,5,0},{0,0,8,3,0,5,6,0,0},{2,0,0,0,0,0,0,0,1},{8,0,0,4,0,7,0,0,6},{0,0,6,0,0,0,3,0,0},{7,0,0,9,0,1,0,0,4},{5,0,0,0,0,0,0,0,2},{0,0,7,2,0,6,9,0,0},{0,4,0,5,0,8,0,7,0}};
    vector<vector<int>> sol1{{9,6,3,1,7,4,2,5,8},{1,7,8,3,2,5,6,4,9},{2,5,4,6,8,9,7,3,1},{8,2,1,4,3,7,5,9,6},{4,9,6,8,5,2,3,1,7},{7,3,5,9,6,1,8,2,4},{5,8,9,7,1,3,4,6,2},{3,1,7,2,4,6,9,8,5},{6,4,2,5,9,8,1,7,3}};

    vector<vector<int>> sud2{{2,0,7,0,3,9,0,0,6},{0,0,0,1,0,2,4,0,8},{1,0,4,0,0,0,7,0,0},{0,6,0,5,0,7,0,0,2},{0,0,0,0,9,0,1,0,0},{3,0,2,0,1,0,9,0,4},{0,0,9,0,5,0,0,3,0},{0,0,0,3,0,6,2,0,0},{4,0,6,0,0,0,0,7,0}};
    vector<vector<int>> sol2{{2,8,7,4,3,9,5,1,6},{6,5,3,1,7,2,4,9,8},{1,9,4,8,6,5,7,2,3},{9,6,1,5,4,7,3,8,2},{5,4,8,2,9,3,1,6,7},{3,7,2,6,1,8,9,5,4},{8,2,9,7,5,4,6,3,1},{7,1,5,3,8,6,2,4,9},{4,3,6,9,2,1,8,7,5}};

    vector<vector<int>> sud3{{4,0,0,1,0,0,2,0,0},{0,9,0,8,0,0,0,7,6},{7,0,0,0,4,0,0,1,0},{0,6,0,0,0,8,3,0,0},{0,0,2,0,7,0,0,8,9},{1,0,3,0,6,0,0,5,0},{3,0,0,0,2,6,0,0,0},{0,7,0,5,0,0,0,2,0},{8,0,4,0,1,0,0,0,3}};
    vector<vector<int>> sol3{{4,5,6,1,9,7,2,3,8},{2,9,1,8,3,5,4,7,6},{7,3,8,6,4,2,9,1,5},{9,6,7,2,5,8,3,4,1},{5,4,2,3,7,1,6,8,9},{1,8,3,9,6,4,7,5,2},{3,1,5,4,2,6,8,9,7},{6,7,9,5,8,3,1,2,4},{8,2,4,7,1,9,5,6,3}};

    vector<vector<int>> sud4{{0,0,4,0,1,0,2,0,0},{2,0,0,0,0,6,0,0,7},{0,9,0,0,3,0,0,4,0},{8,0,6,9,0,1,3,0,0},{0,1,0,0,4,0,0,0,8},{0,0,7,0,0,0,0,0,6},{7,3,0,0,0,0,9,0,0},{0,0,9,0,0,7,0,1,0},{0,4,0,2,5,0,6,0,3}};
    vector<vector<int>> sol4{{3,7,4,5,1,8,2,6,9},{2,8,1,4,9,6,5,3,7},{6,9,5,7,3,2,8,4,1},{8,2,6,9,7,1,3,5,4},{9,1,3,6,4,5,7,2,8},{4,5,7,8,2,3,1,9,6},{7,3,2,1,6,4,9,8,5},{5,6,9,3,8,7,4,1,2},{1,4,8,2,5,9,6,7,3}};

    vector<vector<int>> sud5{{5,0,0,3,0,0,7,0,2},{0,0,7,0,0,0,0,0,0},{3,0,0,8,0,1,5,4,0},{0,5,0,9,0,6,0,0,0},{0,0,0,0,8,0,0,5,1},{6,0,8,2,0,7,0,0,9},{9,8,0,0,0,2,0,1,0},{0,0,0,5,0,0,9,0,4},{7,0,0,1,0,4,0,0,0}};
    vector<vector<int>> sol5{{5,6,1,3,4,9,7,8,2},{8,4,7,6,2,5,1,9,3},{3,9,2,8,7,1,5,4,6},{4,5,3,9,1,6,8,2,7},{2,7,9,4,8,3,6,5,1},{6,1,8,2,5,7,4,3,9},{9,8,4,7,6,2,3,1,5},{1,2,6,5,3,8,9,7,4},{7,3,5,1,9,4,2,6,8}};

    ifstream archivo;
    string texto;

    //archivo.open("sudoku1.txt"); ESTA PARTE DE CODIGO TIENE QUE VER CON LEER LA NOTA DE TEXTO
    //if(archivo.fail()){
        //cout << "No se pudo abrir el archivo\n";
        //exit(1);
    //}
    //while (!archivo.eof()) {
    //    getline(archivo,texto);
    //    cout<<texto<<endl;
    //}
    //archivo.close();
    //display(leer("sudoku1.txt"));//COMO NO FUNCIONA TE DEJO LOS VECTORES YA CONSTRUIDOS PARA QUE PUEDA PROBAR EL CODIGO

    srand(time(NULL));
    int num=1+rand()%(6-1);
    if(num==1) sudoku(sud1,sol1);
    if(num==2) sudoku(sud2,sol2);
    if(num==3) sudoku(sud3,sol3);
    if(num==4) sudoku(sud4,sol4);
    if(num==5) sudoku(sud5,sol5);
    return 0;
}
