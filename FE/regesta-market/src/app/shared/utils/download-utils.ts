import { saveAs } from 'file-saver';

export default class DownloadUtils {

    static downloadFile(file: any, fileName: string, format: string): void {
      const base64str = file;
    // decode base64 string, remove space for IE compatibility
      const binary = atob(base64str.replace(/\s/g, ''));
      //console.log(binary);
      const len = binary.length;
      const buffer = new ArrayBuffer(len);
      const view = new Uint8Array(buffer);
      for (let i = 0; i < len; i++) {
        view[i] = binary.charCodeAt(i);
      }
      //console.log(view);
    // create the blob object with content-type "application/pdf"
      const blob = new Blob([view], { type: this.getMimeType(format)});
    // var url = window.URL.createObjectURL(blob);
      saveAs(blob, fileName + '.' + format);
    }

    static getMimeType(format: string): string{
      let mimeType = null;
      switch (format.toUpperCase()){
        case 'TXT':
        case 'CSV':
          mimeType = 'text/plain';
          break;
        case 'DOC':
          mimeType = 'application/msword';
          break;
        case 'DOCX':
          mimeType = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
          break;
        case 'JPEG':
          mimeType = 'image/jpeg';
          break;
        case 'PDF':
          mimeType = 'application/pdf';
          break;
        case 'PNG':
          mimeType = 'image/png';
          break;
        case 'RTF':
          mimeType = 'application/rtf';
          break;
        case 'XLS':
          mimeType = 'application/vnd.ms-excel';
          break;
        case 'XLSX':
          mimeType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
          break;
        case 'ZIP':
          mimeType = 'application/zip';
          break;
        default:
          mimeType = 'application/octet-stream';
          break;
      }
      return mimeType;
    }

}
