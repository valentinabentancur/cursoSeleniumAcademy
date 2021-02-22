package pruebaNetflix;

import org.testng.annotations.Factory;

public class factoryNetflix {
    @Factory
    public Object [] factoryN(){
        return new Object[]{
                new prueba_netlfix(),
                new prueba_netlfix(),
        };
    }
}
