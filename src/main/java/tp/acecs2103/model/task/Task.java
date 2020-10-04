package tp.acecs2103.model.task;

import tp.acecs2103.commons.util.CollectionUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task in Ace CS2103/T.
 */
public class Task {
    private String index;
    private int weekNumber;
    private String description;
    private LocalDate officialDeadline;
    private LocalDate customizedDeadline;
    private String remark;

    public Task(String index, int weekNumber, String description, LocalDate officialDeadline, LocalDate customizedDeadline, String remark) {
        CollectionUtil.requireAllNonNull(index, weekNumber, description);
        this.index = index;
        this.weekNumber = weekNumber;
        this.description = description;
        this.officialDeadline = officialDeadline;
        this.customizedDeadline = customizedDeadline;
        this.remark = remark;
    }

    public String getIndex() {
        return index;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getOfficialDeadline() {
        return officialDeadline;
    }

    public LocalDate getCustomizedDeadline() {
        return customizedDeadline;
    }

    public boolean contain(String keyword) {

    }
}
