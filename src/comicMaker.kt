
import java.net.URL
import java.util.*

var sayi : String = ""
var sayfa: String = ""
var next : String = ""
var isLast: Boolean = false

    fun main(args: Array<String>) {

        //Bide aq kodunu esnek yap headerı parametre olarak dışardan al
        //pathi dışardan al
        //En son o lanet comemntleri unutma
        //Cu inş


        val header  : String = "http://www.omgbeaupeep.com/comics/mangas/Deadpool/"
        val token   : String = "mangas/Deadpool/"
        val path    : String = "/home/yunti/Desktop/yedek/deneme/"
        val initUrl : String = "http://www.omgbeaupeep.com/comics/"
        val init    : String = "Deadpool/016/"

        var dw      : DownloadImg = DownloadImg(path)
        var fp      : String = ""
        var temp    : String = ""
        var back    : Int

        fp = srcFetch(initUrl+init,header,token)

        dw.SaveFromSource(fp,sayi+"-"+sayfa)
        back = sayi.toInt()


        do{
            temp = initUrl + next


            if (next == null || next.equals("")){
                println("Sonuncaya geldin veya sonunu yakalayamadın")
                break
            }

            fp = srcFetch(temp,header,token)

            if(fp.equals("bos")){
                println("linke ulasilamadi!!")
                break
            }

            if (back != sayi.toInt()){
                back = sayi.toInt()
                dw.newPdf()
            }

            println("sayi: $sayi sayfa: $sayfa")
            dw.SaveFromSource(fp,sayi + "-" + sayfa)


        }while (!isLast)

        dw.newPdf()
        println("GG!!")
    }

    fun parser(prm: String, token: String): String {

        var a: String = prm.split(token)[1]
        var b = a.subSequence(0, a.indexOf("\"")).toString()
        b = b.replace(" ", "%20")
        var say: String = b.subSequence(0, 3).toString()
        var sayf: String = b.substring(b.indexOf("free") + 5, b.indexOf(".jpg"))

        if(sayf.equals(""))
            sayf = "000"

        if(!prm.contains("href")){
            sayi = say
            sayfa = sayf
            isLast = true
            return b
        }


        var a2 : String = prm.substring(prm.indexOf("href")+6,prm.indexOf("><img")-1)

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

