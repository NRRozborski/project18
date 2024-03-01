package com.laguna.project18.WallapopGratuito.model;

public class dadosderol {

    public static void main(String[] args) {
        System.out.println(dadosRol(new int[]{2, 6, 6, 4, 6}));
    }

    public static String dadosRol(int[] dados){
        StringBuilder resultado = new StringBuilder();
        for (int i = 1; i <= dados[0]; i+=2) {
            for (int j = dados[i]+1; j <= dados[i+1]+1; j++) {
                resultado.append(j);
                if(j!=dados[i+1]+1)
                    resultado.append(" ");
                else
                    resultado.append("\n");
            }
        }
        return resultado.toString();
    }
}
