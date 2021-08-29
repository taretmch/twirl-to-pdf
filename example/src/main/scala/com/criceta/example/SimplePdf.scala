package com.criceta.example

import java.io.FileOutputStream
import java.nio.file.{ Paths, Files }
import scala.util.{ Try, Success, Failure }

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder

/** Simple pdf output
 *
 * Output "Hello world" to pdf file
 */
object SimplePdf {

  def main(args: Array[String]): Unit = {
    (for {
      os      <- Try(new FileOutputStream("/tmp/out.pdf"))
      builder  = new PdfRendererBuilder()
    } yield {
      builder.useFastMode()
      builder.withHtmlContent(html.SimplePdf().toString, "")
      builder.toStream(os)
      builder.run()
    }) match {
      case Success(_) => println("Completed with no exception")
      case Failure(e) => println(e.getMessage)
    }
  }
}
