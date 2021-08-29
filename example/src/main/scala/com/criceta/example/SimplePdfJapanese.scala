package com.criceta.example

import java.io.{ File, FileOutputStream }
import java.nio.file.{ Paths, Files }
import scala.util.{ Try, Success, Failure }

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder

/** Output simple pdf file in Japanese
 *
 * Because of some issues, I use `new File` instead of `getClass.getResource()`:
 * - https://github.com/sbt/sbt/issues/3963
 * - https://stackoverflow.com/questions/55493096/listing-files-from-resource-directory-in-sbt-1-2-8
 */
object SimplePdfJapanese {

  def main(args: Array[String]): Unit = {
    // Example fonts are not included in public code.
    // If you use custom fonts, download some fonts and move to appropriate directory.
    val fontUrl = "example/src/main/resources/fonts/ipaexg.ttf"
    (for {
      os      <- Try(new FileOutputStream("/tmp/out-japanese.pdf"))
      font    <- Try(new File(fontUrl))
      builder  = new PdfRendererBuilder()
    } yield {
      builder.useFastMode()
      builder.withHtmlContent(html.SimplePdfJapanese(font.getAbsolutePath()).toString, "")
      builder.toStream(os)
      builder.run()
    }) match {
      case Success(_) => println("Completed with no exception")
      case Failure(e) => println(e.getMessage)
    }
  }
}
