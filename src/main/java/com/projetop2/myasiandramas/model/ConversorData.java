package com.projetop2.myasiandramas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConversorData {

    public static String converterData(LocalDate data){
        if(data==null)
            return "";
        String dataConvertida = data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
        return dataConvertida;
    }

    public static String converterData(LocalDateTime dataHora){
         if(dataHora==null)
            return "";
        String dataConvertida = dataHora.getDayOfMonth() + "/" + dataHora.getMonthValue() + "/" + dataHora.getYear();
        return dataConvertida;
    }

}
