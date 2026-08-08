#include <bits/stdc++.h>
typedef long long ll;
using namespace std;

//Método para guardar todos los números dentro de N
//Que NO tengan CSB


vector<int> NoConsecutiveSetBits(ll n) {
    vector<int> nValidos;
    //Calcular 2eN para maxRange
    int dosN = (1 << n);
    for (int i = 1; i < dosN; ++i) {
        //Si el n revisando y el shifted a la izq
        // En AND dan 0 significa que ese n NO tiene CSB
        if ((i & (i << 1)) == 0) {
            nValidos.push_back(i);
        }
    }
    return nValidos;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ll input;
    cin >> input;
    vector<int> res = NoConsecutiveSetBits(input);
    //Size
    cout << res.size() << endl;
    //Cuales n° son
    for (int i: res) {
        cout << i << " ";
    }
    


    return 0;
}
