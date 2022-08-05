class Solution {
    public int compareVersion(String version1, String version2) {
        version1 = version1 + '.'; 
        version2 = version2 + '.'; 
        
        int v1 = version1.length();
        int v2 = version2.length();
        int i = 0, j = 0;
        
        for (; i < v1 && j < v2; i++, j++){
            int temp_i = i;
            while (temp_i < v1  &&  version1.charAt(temp_i) != '.')
                temp_i++;
            
            int v1_revision = Integer.parseInt(version1.substring(i, temp_i));
            i = temp_i;
            
            
            int temp_j = j;
            while (temp_j < v2  &&  version2.charAt(temp_j) != '.')
                temp_j++;
            
            int v2_revision = Integer.parseInt(version2.substring(j, temp_j));
            j = temp_j;
            
            if (v1_revision > v2_revision) 
                return 1;
            if (v2_revision > v1_revision) 
                return -1;
        }
        
        while (i < v1){
            int temp_i = i;
            while (temp_i < v1  &&  version1.charAt(temp_i) != '.')
                temp_i++;
            
            int v1_revision = Integer.parseInt(version1.substring(i, temp_i));
            i = temp_i;
            
            if (v1_revision != 0) 
                return 1;
            i++;
        }
        
        while (j < v2){
            int temp_j = j;
            while (temp_j < v2  &&  version2.charAt(temp_j) != '.')
                temp_j++;
            
            int v2_revision = Integer.parseInt(version2.substring(j, temp_j));
            j = temp_j;
         
            if (v2_revision != 0) 
                return -1;
            j++;
        }
        return 0;
    }
    

}