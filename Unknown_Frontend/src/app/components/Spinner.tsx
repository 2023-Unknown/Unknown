import Image from "next/image";
import SpinnerIcon from '../../../public/spinner1.gif';

export const Spinner = () => {
    return(
        <div>
            <h3>Finding your chat mate...</h3>

            {/* 1. 지구 자전 모형 (유료)
            https://loading.io/spinner/earth/-earth-globe-map-rotate */}

            {/* 2. 지구 자전 모형 (무료), 흰색*/}
            <iframe src="https://giphy.com/embed/bEKmZEYwwNQFUkeTDv" width="480" height="416" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/stickers/bank-australia-responsible-banking-bankaust-bEKmZEYwwNQFUkeTDv">via GIPHY</a></p>
            <Image src="/spinner_1.gif" alt="spinner" width="64" height="64" />

            {/* 3. 지구 자전 모형 (무료), 지구색*/}
            <iframe src="https://giphy.com/embed/0O5NwsKlbgN2EF3q6x" width="480" height="480" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/world-earth-globe-0O5NwsKlbgN2EF3q6x">via GIPHY</a></p>
            <Image src="/spinner_2.gif" alt="spinner" width="64" height="64" />

            {/* 4. 일반 스피너(무료), 색상 지정 가능
            https://loading.io/spinner/spin/-spinner-ball-circle-rotate-rosary-loader-ajax*/}

            {/* 5. 일반 스피너(무료), 색상 지정 가능 
            https://schum123.github.io/svelte-loading-spinners/?ref=madewithsvelte.com*/}
            </div>
    );
}