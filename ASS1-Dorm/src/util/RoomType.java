/**
 *
 * @author Group 4
 */
package util;

public enum RoomType {
    
    DOUBLE(4),
    TRIPLE(6);
    
    private final int beds;
    
    RoomType(int beds) {
        this.beds = beds;
    }
    
    public int getBeds() {
        return beds;
    }
    
}
