package com.example.mycryptonow.models;

public class ErroresStatus {

    public static final String error1001 = "Esta API KEY es invalida" ;
    public static final String error1002 = "Falta la API KEY";
    public static final String error1003 = "Tu API KEY debe de estar activada, verificar los planes en \"pro.coinmarketcap.com/account/plan\"";
    public static final String error1004 = "A expridado el plan de tu API KEY";
    public static final String error1005 = "Para esta consulta se requiere una API KEY";
    public static final String error1006 = "La subscripcion de tu API KEY ya no es soportada";
    public static final String error1007 = "Tu API KEY fue desabilidata, por favor contante a soporte";
    public static final String error1008 = "Tu numero de consulta a exedido tu limte de creditos";
    public static final String error1009 = "Has supera tu limite diario";
    public static final String error1010 = "Has superado tu limite mensual";
    public static final String error1011 = "Has alcanzado el limite de velocidad de tu consulta";

    public static String buscarError(int error){

        switch (error){
            case 1001:
                return error1001;
            case 1002:
                return error1002;
            case 1003:
                return error1003;
            case 1004:
                return error1004;
            case 1005:
                return error1005;
            case 1006:
                return error1006;
            case 1007:
                return error1007;
            case 1008:
                return error1008;
            case 1009:
                return error1009;
            case 1010:
                return error1010;
            case 1011:
                return error1011;
            default:
                return "No se encontre el codigo de error : "+error;
        }
    }
}
