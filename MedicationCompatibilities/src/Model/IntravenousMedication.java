
package Model;

/**
 *
 * @author ben garrison
 */
public class IntravenousMedication {
    
    private String medication;
    private String d5W;
    private String normalSaline;
    private String halfNormalSaline;
    private String lactatedRingers;
    private String d10W;
    private String sterileWater;    
    
    public IntravenousMedication(String medication, String d5W, String normalSaline, String halfNormalSaline,
            String lactatedRingers, String d10W, String sterileWater) {
        
        this.medication = medication;
        this.d5W = d5W;
        this.normalSaline = normalSaline;
        this.halfNormalSaline = halfNormalSaline;
        this.lactatedRingers = lactatedRingers;
        this.d10W = d10W;
        this.sterileWater = sterileWater;
    }

    public IntravenousMedication(String medication) {
        
        this.medication = medication;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getD5W() {
        return d5W;
    }

    public void setD5W(String d5W) {
        this.d5W = d5W;
    }

    public String getNormalSaline() {
        return normalSaline;
    }

    public void setNormalSaline(String normalSaline) {
        this.normalSaline = normalSaline;
    }

    public String getHalfNormalSaline() {
        return halfNormalSaline;
    }

    public void setHalfNormalSaline(String halfNormalSaline) {
        this.halfNormalSaline = halfNormalSaline;
    }

    public String getLactatedRingers() {
        return lactatedRingers;
    }

    public void setLactatedRingers(String lactatedRingers) {
        this.lactatedRingers = lactatedRingers;
    }

    public String getD10W() {
        return d10W;
    }

    public void setD10W(String d10W) {
        this.d10W = d10W;
    }

    public String getSterileWater() {
        return sterileWater;
    }

    public void setSterileWater(String sterileWater) {
        this.sterileWater = sterileWater;
    }
    
}
