
import java.net.URL
import java.util.*

var sayi : String = ""
var sayfa: String = ""
var next : String = ""

    fun main(args: Array<String>) {

        println("Hi There")

        val header  : String = "http://www.omgbeaupeep.com/comics/mangas/Deadpool/"
        val token   : String = "mangas/Deadpool/"
        val path    : String = "/home/yunti/Desktop/yedek/deneme/"
        val initUrl : String = "http://www.omgbeaupeep.com/comics/"
        val init    : String = "Deadpool/016/2/"
        var dw: DownloadImg

        var fp = srcFetch(initUrl+init,header,token)
        if (!fp.equals("bos")){
            //println(next)
            dw = DownloadImg(fp,path,sayi + "-" + sayfa);
            var temp = initUrl + next
            println(temp)
            fp = srcFetch(temp,header,token)
            dw = DownloadImg(fp,path,sayi + "-" + sayfa);
            temp = initUrl + next
            println(temp)
        }
        else
            println("GG")

    }

    fun parser(prm: String, token: String): String {
        var a: String = prm.split(token)[1]
        var b = a.subSequence(0, a.indexOf("\"")).toString()
        b = b.replace(" ", "%20")
        var say: String = b.subSequence(0, 3).toString()
        var sayf: String = b.substring(b.indexOf("free") + 5, b.indexOf(".jpg"))
        var a2 : String = prm.substring(prm.indexOf("href")+6,prm.indexOf("><img")-1)
        println(a2)
        next = a2
        sayi = say
        sayfa = sayf

        return b
    }

    fun srcFetch(u: String, head: String,tkn: String):String{
        var url : URL = URL(u)
        var s: Scanner = Scanner(url.openStream())
        var temp: String
        var ret: String = "bos"

        while (s.hasNext()){
            temp = s.nextLine()
            if (temp.contains(tkn)){
                ret = head.plus(parser(temp,tkn))
                return ret
            }

        }
        return ret
    }

