package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import entities.Book;

public class BookQuantityTest {

    @Test
    public void deveRetornarTrueParaQuantidadePositiva() {
        
        boolean resultado = Book.quantidadeValida(5);
        assertTrue(resultado);
    }

    @Test
    public void deveRetornarFalseParaQuantidadeZero() {
      
        boolean resultado = Book.quantidadeValida(0);
        assertFalse(resultado);
    }

    @Test
    public void deveRetornarFalseParaQuantidadeNegativa() {
       
        boolean resultado = Book.quantidadeValida(-3);
        assertFalse(resultado);
    }
}
