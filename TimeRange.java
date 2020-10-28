//1715821
package coursework3;

public class TimeRange {

    private boolean wholeMonth;
    private int start;
    private int end;

    public TimeRange(boolean wholeMonth, int start, int end) {
        this.wholeMonth = wholeMonth;
        this.start = start;
        this.end = end;
    }

    public boolean isWholeMonth() {
        return wholeMonth;
    }

    public void setWholeMonth(boolean wholeMonth) {
        this.wholeMonth = wholeMonth;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isInTimeRange(int day) {
        if (isWholeMonth()) {
            return true;
        }
        return day >= start && day <= end;
    }
}
