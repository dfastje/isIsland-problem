package me.dfastje.reverselistexample.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MatrixGroupingServiceTest {

    //Test Data
    boolean[][] geographicMatrix_2x2;
    boolean[][] geographicMatrix_5x5;
    boolean[][] geographicMatrix_2x5;

    //I've been meaning to find a better way to handle Bean creation in Unit tests without Mockito:
    //  https://www.baeldung.com/spring-boot-testing
    @TestConfiguration
    static class ReverseListServiceConfiguration {
        @Bean
        public MatrixGroupingService matrixGroupingServiceBean() {
            return new MatrixGroupingService();
        }
    }

    @Autowired
    private MatrixGroupingService matrixGroupingService;

    @BeforeEach
    void setupData(){
        geographicMatrix_2x2 = new boolean[][]{
                {true, false},
                {true, false}
        };


        geographicMatrix_5x5 = new boolean[][]{
                {true,  true,  false, false, false},
                {false, true,  false, false, true},
                {true,  false, false, true,  true},
                {false, false, false, false, false},
                {true,  false, true,  false, true}
        };

        geographicMatrix_2x5 = new boolean[][]{
                {true,  true},
                {false, true},
                {true,  false},
                {false, false},
                {true,  false}
        };
    }

    @Test
    void countIslandsTest(){
        Assertions.assertEquals( matrixGroupingService.countIslands(geographicMatrix_5x5) , 6 );
        Assertions.assertEquals( matrixGroupingService.countIslands(geographicMatrix_2x2) , 1 );
        Assertions.assertEquals( matrixGroupingService.countIslands(geographicMatrix_2x5) , 3 );
    }

}