package LSP;

public class LibraryItem{
    protected String title;
    

    public LibraryItem(String title) {
        this.title = title;
    }

    // LibraryItem은 도서관 항목 목록을 보고하는 클래스이다.
    // 사용자가 도서 목록(title)을 검색하면 도서 목록이 있는지 답을 줘야한다.(generateReport). 
    // 따라서 LibraryItem 클래스에 generateReport 메서드가 존재해야 한다고 생각한다.
    public void generateReport() {
        System.out.print("Title: " + title);
    }

}
