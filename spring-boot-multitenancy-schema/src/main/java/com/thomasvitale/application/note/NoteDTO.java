package com.thomasvitale.application.note;

import java.util.Objects;

public class NoteDTO {
    private String title;
    private String content;

    public NoteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that == null) {
            return false;
        } else if (getClass() != that.getClass()) {
            return false;
        }

        NoteDTO thatNote = (NoteDTO) that;
        return Objects.equals(this.title, thatNote.title)
                && Objects.equals(this.content, thatNote.content);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }
}
