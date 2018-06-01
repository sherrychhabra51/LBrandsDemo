package lbrands.com.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "repo")
public class RepoResponseModel implements Parcelable{

    @PrimaryKey
    public long id;
    public String name;
    public String full_name;
    public LoginResponseModel owner;
    public String html_url;
    public String description;
    public String url;
    public Date created_at;
    public Date updated_at;
    public Date pushed_at;
    public String git_url;
    public String ssh_url;
    public String clone_url;
    public String svn_url;
    public String homepage;
    public int stargazers_count;
    public int watchers_count;
    public String language;
    public boolean has_issues;
    public boolean has_downloads;
    public int size;
    public boolean has_wiki;
    public boolean has_pages;
    public int forks_count;
    public int open_issues_count;
    public int forks;
    public int open_issues;
    public int watchers;
    public String default_branch;
    public RepoResponseModel(){

    }
    public RepoResponseModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        full_name = in.readString();
        owner = in.readParcelable(LoginResponseModel.class.getClassLoader());
        html_url = in.readString();
        description = in.readString();
        url = in.readString();
        git_url = in.readString();
        ssh_url = in.readString();
        clone_url = in.readString();
        svn_url = in.readString();
        homepage = in.readString();
        stargazers_count = in.readInt();
        watchers_count = in.readInt();
        language = in.readString();
        has_issues = in.readByte() != 0;
        has_downloads = in.readByte() != 0;
        size = in.readInt();
        has_wiki = in.readByte() != 0;
        has_pages = in.readByte() != 0;
        forks_count = in.readInt();
        open_issues_count = in.readInt();
        forks = in.readInt();
        open_issues = in.readInt();
        watchers = in.readInt();
        default_branch = in.readString();
    }

    public static final Creator<RepoResponseModel> CREATOR = new Creator<RepoResponseModel>() {
        @Override
        public RepoResponseModel createFromParcel(Parcel in) {
            return new RepoResponseModel(in);
        }

        @Override
        public RepoResponseModel[] newArray(int size) {
            return new RepoResponseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(full_name);
        parcel.writeParcelable(owner, i);
        parcel.writeString(html_url);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(git_url);
        parcel.writeString(ssh_url);
        parcel.writeString(clone_url);
        parcel.writeString(svn_url);
        parcel.writeString(homepage);
        parcel.writeInt(stargazers_count);
        parcel.writeInt(watchers_count);
        parcel.writeString(language);
        parcel.writeByte((byte) (has_issues ? 1 : 0));
        parcel.writeByte((byte) (has_downloads ? 1 : 0));
        parcel.writeInt(size);
        parcel.writeByte((byte) (has_wiki ? 1 : 0));
        parcel.writeByte((byte) (has_pages ? 1 : 0));
        parcel.writeInt(forks_count);
        parcel.writeInt(open_issues_count);
        parcel.writeInt(forks);
        parcel.writeInt(open_issues);
        parcel.writeInt(watchers);
        parcel.writeString(default_branch);
    }
}
