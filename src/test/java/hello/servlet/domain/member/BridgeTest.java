package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class BridgeTest {

    int totalTime = 0;

    @AfterEach
    void resetData(){
        totalTime = 0;
    }

    @Test
    void 다리건너기(){
        int num = 7;
        //int num = 4;
        int[] speed = {10,20,30,40,50,60,70};
        //int[] speed = {1,2,5,10};


        Map<Integer, Integer> spotA = new HashMap<Integer, Integer>();
        Map<Integer, Integer> spotB = new HashMap<Integer, Integer>();

        // spotA에 사람들 넣기
        for (int i = 0; i < speed.length ; i++) {
            spotA.put(i, speed[i]);
        }

        while(!spotA.isEmpty()){
            if(spotA.size() >= 2){
                빠른사람_둘_이동(spotA, spotB);
                if(spotA.size() >= 1){
                    빠른사람_하나_이동(spotB, spotA);
                    느린사람_둘_이동(spotA, spotB);
                    if(spotA.size() >= 1){
                        빠른사람_하나_이동(spotB, spotA);
                    }
                }
            }else if(spotA.size() >= 1){
                빠른사람_하나_이동(spotB, spotA);
            }
        }

        System.out.println("totalTime = " + totalTime);
        Assertions.assertThat(totalTime).isEqualTo(280);
    }

    void 빠른사람_둘_이동(Map<Integer, Integer> src, Map<Integer, Integer> dst){
        Integer key1;
        Integer key2;

        key1 = 최소값찾기(src);
        dst.put(key1, src.get(key1));
        src.remove(key1);

        key2 = 최소값찾기(src);
        dst.put(key2, src.get(key2));
        src.remove(key2);

        totalTime += dst.get(key2);
    }

    void 빠른사람_하나_이동(Map<Integer, Integer> src, Map<Integer, Integer> dst){
        Integer key1;

        key1 = 최소값찾기(src);
        dst.put(key1, src.get(key1));
        src.remove(key1);

        totalTime += dst.get(key1);
    }

    void 느린사람_둘_이동(Map<Integer, Integer> src, Map<Integer, Integer> dst){
        Integer key1;
        Integer key2;

        key1 = 최대값찾기(src);
        dst.put(key1, src.get(key1));
        src.remove(key1);

        key2 = 최대값찾기(src);
        dst.put(key2, src.get(key2));
        src.remove(key2);

        totalTime += dst.get(key1);
    }



    Integer 최소값찾기(Map<Integer, Integer> src) {
        Integer result = null;
        Integer temp = null;

        for (Integer key : src.keySet()) {
            if(temp == null){
                temp = src.get(key);
                result = key;
            }
            if(temp > src.get(key)){
                result = key;
            }
        }
        return result;
    }

    Integer 최대값찾기(Map<Integer, Integer> src) {
        Integer result = null;
        Integer temp = null;

        for (Integer key : src.keySet()) {
            if(temp == null){
                temp = src.get(key);
                result = key;
            }
            if(temp < src.get(key)){
                result = key;
            }
        }
        return result;
    }

}
